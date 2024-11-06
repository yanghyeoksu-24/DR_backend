package com.dr.service.user;

import com.dr.domain.CustomOAuth2User;
import com.dr.dto.user.KakaoUsersDTO;
import com.dr.mapper.user.KakaoUsersMapper;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final KakaoUsersMapper kakaoUsersMapperuserMapper;

    public CustomOAuth2UserService(KakaoUsersMapper kakaoUsersMapperuserMapper) {
        this.kakaoUsersMapperuserMapper = kakaoUsersMapperuserMapper;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // OAuth2로부터 받은 사용자 정보
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // 카카오는 attributes 내에 kakao_account 객체에 사용자 정보를 담고 있음
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");

        // 프로필 정보는 kakao_account 내의 profile 객체에 있음
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        String name = (String) profile.get("nickname");
        String profilePic = (String) profile.get("profile_image_url");
        // 이메일 정보 추출
        String accountEmail = (String) profile.get("email");

        // 카카오의 경우 ID는 최상위 attributes 객체의 id 필드에 있음
        String providerId = attributes.get("id").toString();

        System.out.println("이름 : " + name);
        System.out.println("사진 : " + profilePic);
        System.out.println("이메일 : " + accountEmail);


        // DTO 객체에 정보 설정
        KakaoUsersDTO user = new KakaoUsersDTO();
        user.setProviderId(providerId); // OAuth2 제공자에서의 사용자 고유 ID
        user.setName(name);
        user.setAccountEmail(accountEmail);
        user.setProfilePic(profilePic);

        user.setProvider(userRequest.getClientRegistration().getRegistrationId());
        // OAuth2 제공자의 이름 (예: "kakao")

        // DB에 사용자 정보 저장 또는 업데이트
        KakaoUsersDTO existingUser = kakaoUsersMapperuserMapper.findByProviderId(providerId);
        if (existingUser == null) {
            // 사용자가 새로운 경우, DB에 저장
            System.out.println(user);
        } else {
            // 이미 존재하는 사용자인 경우, 필요한 정보 업데이트
            existingUser.setName(name); // 예시: 이름 정보 업데이트
            existingUser.setProfilePic(profilePic); // 예시: 프로필 사진 정보 업데이트
            kakaoUsersMapperuserMapper.updateUser(existingUser);
        }

        System.out.println(oAuth2User);

//        return oAuth2User;
        return new CustomOAuth2User(oAuth2User, name, profilePic, providerId, accountEmail);
    }
}
