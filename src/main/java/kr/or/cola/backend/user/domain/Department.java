package kr.or.cola.backend.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Department {
        ME("me"),
        ENV("env"),
        IE("ie"),
        CE("ce"),
        CHE("che"),
        TSE("tse"),
        MSE("mse"),
        ARCH("arch"),
        CHEMBIO("chembio"),
        ISE("ise"),
        ECE("ece"),
        SW("sw"),
        MEDIA("media"),
        MDC("mdc"),
        SECURITY("security"),
        MATH("math"),
        PHYSICS("physics"),
        CHEM("chem"),
        BIOLOGY("biology"),
        ABIZ("abiz"),
        EBIZ("ebiz"),
        FE("fe"),
        GB("gb"),
        KOR("kor"),
        ELL("ell"),
        FRANCE("france"),
        HISTORY("history"),
        CULTURE("culture"),
        ECONOMY("economy"),
        PBA("pba"),
        APSY("apsy"),
        SOCI("soci"),
        POL("pol"),
        SPORTS_LEZ("sports_lez"),
        MEDICINE("medicine"),
        NURSING("nursing"),
        PHARM("pharm");

        private final String key;
}