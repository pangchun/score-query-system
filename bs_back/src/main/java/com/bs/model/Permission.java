package com.bs.model;

import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 后台用户权限表
 * </p>
 *
 * @author ¶¬¼ÅÑ©
 * @since 2021-01-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Permission implements Serializable {

    private Long id;
    private Long pid;
    private String name;
    private String value;
    private String icon;
    private Integer type;
    private String uri;
    private Integer status;
    private LocalDateTime createTime;
    private Integer sort;
}
