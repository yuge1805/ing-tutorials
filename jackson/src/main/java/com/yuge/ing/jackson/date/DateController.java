package com.yuge.ing.jackson.date;

import com.yuge.ing.jackson.date.dto.LocalDateTimeDTO;
import com.yuge.ing.jackson.date.vo.LocalDateTimeVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author: yuge
 * @date: 2022/12/14
 **/
@RestController
@RequestMapping("/date")
public class DateController {

    @GetMapping("/now")
    public R<Date> now() {
        return R.success(new Date());
    }

    @GetMapping("/local-date-time/now")
    public R<LocalDateTime> localDateTimeNow() {
        return R.success(LocalDateTime.now());
    }

    @GetMapping("/vo")
    public R<LocalDateTimeVO> vo() {
        LocalDateTimeVO vo = new LocalDateTimeVO();
        vo.setLocalDateTime(LocalDateTime.now());
        return R.success(vo);
    }

    @PostMapping("/submit")
    public R<String> submit(@RequestBody LocalDateTimeDTO dto) {
        LocalDateTime localDateTime = dto.getLocalDateTime();
        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return R.success(format);
    }

}
