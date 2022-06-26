package com.kakaopay.issueapi;

import com.kakaopay.issueapi.api.ctr.BarcodeRestController;
import com.kakaopay.issueapi.api.dto.CoreRequestDTO;
import com.kakaopay.issueapi.api.dto.CoreResponseDTO;
import com.kakaopay.issueapi.api.dto.MemberDTO;
import com.kakaopay.issueapi.api.repo.MemberRepository;
import com.kakaopay.issueapi.api.svc.BarcodeService;
import com.kakaopay.issueapi.api.svc.impl.BarcodeServiceImpl;
import com.kakaopay.issueapi.api.vo.MemberVo;
import com.kakaopay.issueapi.cmm.constant.ResponseCode;
import com.kakaopay.issueapi.cmm.constant.TypeConstant;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class BarcodeRepoTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("멤버 정보가 DB에 저장이 잘 되는지 확인")
    public void saveMember() {
        // given
        MemberVo member = new MemberVo();
        member.setMemberId(123);
        member.setName("chpark");
        member.setBarcode("11124");
        // when
        MemberVo savedMember = memberRepository.save(member);
        // then
        Assertions.assertThat(savedMember.getBarcode()).isNotNull();
//        Assertions.assertThat(member).isSameAs(savedMember);
//        Assertions.assertThat(member.getName()).isEqualTo(savedMember.getName());
//        Assertions.assertThat(memberRepository.count()).isEqualTo(1);
    }




}
