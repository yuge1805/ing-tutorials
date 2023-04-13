package com.yuge.ing.jackson.xml;

import com.yuge.ing.jackson.xml.dto.MpFollowEventDTO;
import com.yuge.ing.jackson.xml.vo.MpFollowEventVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yuge
 * @date: 2023/4/3
 **/
@Slf4j
@RestController
@RequestMapping("/mp/auth")
public class XmlController {

    @RequestMapping(path = "/checkSignature/{appid}", consumes = {"application/xml", "text/xml"}, produces = "application/xml;charset=utf-8")
    public MpFollowEventVO mpFollowEvent(@RequestParam(value = "signature", required = false) String signature,
                                         @RequestParam(value = "timestamp", required = false) String timestamp,
                                         @RequestParam(value = "nonce", required = false) String nonce,
                                         @RequestParam(value = "echostr", required = false) String echostr,
                                         @RequestBody MpFollowEventDTO mpFollowEventDTO) {
        log.info(signature);
        log.info(timestamp);
        log.info(nonce);
        log.info(echostr);
        log.info(mpFollowEventDTO.toString());
        return new MpFollowEventVO();
    }

}
