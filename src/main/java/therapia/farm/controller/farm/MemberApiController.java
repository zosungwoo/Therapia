package therapia.farm.controller.farm;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import therapia.farm.service.farm.MemberService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    //처음 가입한 회원이면 DB에 로그인하고 있는 회원이면 memberid 리턴
    @PostMapping("/api/member/add")
    public Map<String, String> addMember(@RequestBody Map<String,String> map) {
        String nickname = map.get("nickname");
        String email = map.get("email");
        Long id = memberService.createMember(nickname,email);
        Map<String, String> map1 = new HashMap<>();
        map1.put("memberid", id.toString());
        System.out.println(map1);
        return map1;
    }

    //닉네임 변경
    @PostMapping("/api/member/change")
    public void changeMember(@RequestBody Map<String, String> map) {
        String nickname = map.get("nickname");
        String id = map.get("id");
        memberService.updateMember(Long.valueOf(id), nickname);
    }
}
