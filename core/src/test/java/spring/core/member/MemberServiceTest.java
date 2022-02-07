package spring.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.core.AppConfig;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        // given : 이러한 상황이 주어졌을 때
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when : 이 타이밍에
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then : 이러한 동작을 수행할거야
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
