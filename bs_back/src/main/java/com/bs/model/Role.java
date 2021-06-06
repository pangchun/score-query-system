package com.bs.model;

import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 后台用户角色表
 * </p>
 *
 * @author ¶¬¼ÅÑ©
 * @since 2021-01-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Integer adminCount;
    private LocalDateTime createTime;
    private Integer status;
    private Integer sort;

    private Integer permissionId;//权限id
}
