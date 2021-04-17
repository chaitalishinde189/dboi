package com.dboi.trademanagement.model;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Trade {

    @NotBlank(message = "[tradeId] cannot be blank")
    private String tradeId;

    @NotNull(message = "[version] cannot be null")
    @Min(value = 1, message = "Minimum value for [version] is 1")
    @Max(value = 1000, message = "Maximum value for [version] is 1000")
    private Integer version;

    @NotBlank(message = "[counterPartyId] cannot be blank")
    private String counterPartyId;

    @NotBlank(message = "[bookId] cannot be blank")
    private String bookId;

    @NotNull(message = "[maturityDate] cannot be null")
    private Date maturityDate;

    @NotNull(message = "[createdDate] cannot be null")
    private Date createdDate;

    private Boolean expired;

}
