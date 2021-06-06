package com.bs.model;

import lombok.*;
import java.io.Serializable;

/**
 * <p>
 * 后台用户和角色关系表
 * </p>
 *
 * @author ¶¬¼ÅÑ©
 * @since 2021-01-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRoleRelation implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer roleId;

    private Integer[] roleIds;


}
