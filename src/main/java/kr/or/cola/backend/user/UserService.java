package kr.or.cola.backend.user;

import kr.or.cola.backend.aws.service.AwsS3Service;
import kr.or.cola.backend.oauth.dto.OAuthAttributes;
import kr.or.cola.backend.oauth.dto.SessionUser;
import kr.or.cola.backend.todo.folder.FolderService;
import kr.or.cola.backend.todo.folder.domain.Folder;
import kr.or.cola.backend.todo.folder.domain.FolderRepository;
import kr.or.cola.backend.user.domain.Role;
import kr.or.cola.backend.user.domain.User;
import kr.or.cola.backend.user.domain.UserRepository;
import kr.or.cola.backend.auth.dto.SignUpRequestDto;
import kr.or.cola.backend.user.presentation.dto.UserResponseDto;
import kr.or.cola.backend.user.presentation.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final HttpSession httpSession;

    private final UserRepository userRepository;

    private final FolderRepository folderRepository;

    private final FolderService folderService;

    private final AwsS3Service awsS3Service;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);

        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())), attributes.getAttributes(), attributes.getNameAttributeKey());
    }

    public UserResponseDto signUp(Long userId, SignUpRequestDto signUpRequestDto) {
        User user = findUserById(userId);
        Folder defaultFolder = folderRepository.save(
            Folder.builder()
            .user(user)
            .name("일반")
            .color("#ffffff")
            .build());

        user.signUp(Role.USER,
                signUpRequestDto.getName(),
                signUpRequestDto.getAjouEmail(),
                signUpRequestDto.getGitEmail(),
                signUpRequestDto.getDepartment(),
                defaultFolder.getFolderId().toString(),
                null,
                true
            );

        return new UserResponseDto(userRepository.save(user));
    }

    public void updateContent(Long userId, UserUpdateRequestDto requestDto) {
        User user = findUserById(userId);
        user.updateContent(requestDto.getName(),
            requestDto.getDepartment(), requestDto.getGitEmail());
    }

    public void updateProfile(Long userId, MultipartFile profileImage) {
        User user = findUserById(userId);
        user.updateProfile(awsS3Service.uploadFile(profileImage));
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
            .orElse(attributes.toEntity());

        return userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElse(null);
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(()
                -> new IllegalArgumentException(
                    "Invalid User ID: id=" + userId)
            );
    }
}
