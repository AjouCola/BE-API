package kr.or.cola.backend.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Major {
        ME("me", "기계공학과"),
        ENV("env", "환경안전공학과"),
        IE("ie", "산업공학과"),
        CE("ce", "건설시스템공학과"),
        CHE("che", "화학공학과"),
        TSE("tse", "교통시스템공학과"),
        MSE("mse", "신소재공학과"),
        ARCH("arch", "건축학과"),
        CHEMBIO("chembio", "응용화학생명공학과"),
        ISE("ise", "융합시스템공학과"),
        ECE("ece", "전자공학과"),
        SW("sw", "소프트웨어학과"),
        MEDIA("media", "미디어학과"),
        MDC("mdc", "국방디지털융합학과"),
        SECURITY("security", "사이버보안학과"),
        MATH("math", "수학과"),
        PHYSICS("physics", "물리학과"),
        CHEM("chem", "화학과"),
        BIOLOGY("biology", "생명과학과"),
        ABIZ("abiz", "경영학과"),
        EBIZ("ebiz", "e-비즈니스학과"),
        FE("fe", "금융공학과"),
        GB("gb", "글로벌경영학과"),
        KOR("kor", "국어국문학과"),
        ELL("ell", "영어영문학과"),
        FRANCE("france", "불어불문학과"),
        HISTORY("history", "사학과"),
        CULTURE("culture", "문화콘텐츠학과"),
        ECONOMY("economy", "경제학과"),
        PBA("pba", "행정학과"),
        APSY("apsy", "심리학과"),
        SOCI("soci", "사회학과"),
        POL("pol", "정치외교학과"),
        SPORTS_LEZ("slez", "스포츠레저학과"),
        MEDICINE("medicine", "의학과"),
        NURSING("nursing", "간호학과"),
        PHARM("pharm", "약학대학");

        private final String key;
        private final String name;
}