package com.bol.mancala.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Pit {
    private int index;
    private int stones;
}
