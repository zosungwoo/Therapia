package therapia.farm.controller.farm;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import therapia.farm.exception.CustomException;
import therapia.farm.service.farm.MemberService;

import java.util.HashMap;
import java.util.Map;

@Api(tags = {"Member API"})
@Log4j2
@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    // 처음 가입한 회원이면 DB에, 로그인하고 있는 회원이면 memberid 리턴
    @ApiOperation(value = "회원 생성",
            notes = "개별 회원 생성\n(처음 가입한 회원이면 DB에, 로그인하고 있는 회원이면 member id 리턴)")
    @PostMapping("/api/members")
    public Map<String, String> addMember(@RequestBody Map<String,String> map) throws Exception {
        String nickname = map.get("nickname");
        String email = map.get("email");
        if(memberService.findMemberByEmail(email) == null) {
            if(memberService.findMemberByNickname(nickname) != null) {
                throw new CustomException("존재하는 닉네임");
            }
        }

        Long memberId = memberService.createMember(nickname,email);
        Map<String, String> map1 = new HashMap<>();
        map1.put("memberid", memberId.toString());
        return map1;
    }

    //닉네임 변경
    @ApiOperation(value = "회원 닉네임 수정", notes = "회원 닉네임 수정")
    @PutMapping("/api/members")
    public void updateMember(@RequestBody Map<String, String> map) throws Exception {
        String id = map.get("id");
        String nickname = map.get("nickname");
        if(memberService.findMemberById(Long.valueOf(id)) == null) {
            throw new CustomException("존재하지 않는 사용자");
        }
        if(memberService.findMemberByNickname(nickname) != null) {
            throw new CustomException("존재하는 닉네임");
        }

        memberService.updateMember(Long.valueOf(id), nickname);
    }
}
