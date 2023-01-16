package com.example.apibasic.post.api.validate;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.YearMonth;

public class Card {

    @NotBlank
    private String cardNo;

    @JsonFormat(pattern = "yyyy-MM")
    @Future //미래 날짜인지 검사
    private YearMonth validMonth;
}
