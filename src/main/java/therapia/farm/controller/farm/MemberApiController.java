package therapia.farm.controller.farm;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import therapia.farm.dto.farm.MemberRequestDto;
import therapia.farm.dto.farm.MemberResponseDto;
import therapia.farm.dto.farm.MemberUpdateDto;
import therapia.farm.service.farm.MemberService;

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
    public ResponseEntity<MemberResponseDto> addMember(@RequestBody MemberRequestDto memberRequestDto) {
        MemberResponseDto memberResponseDto = memberService.createMember(memberRequestDto);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    //닉네임 변경
    @ApiOperation(value = "회원 닉네임 수정", notes = "회원 닉네임 수정")
    @PutMapping("/api/members")
    public ResponseEntity<MemberResponseDto> updateMember(@RequestBody MemberUpdateDto memberRequestDto){
        MemberResponseDto memberResponseDto = memberService.updateMember(memberRequestDto);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }
}
