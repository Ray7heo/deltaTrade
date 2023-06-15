package com.theo.deltaTrade.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
   private Long toId;
   private Long commodityId;
   private String content;
}
