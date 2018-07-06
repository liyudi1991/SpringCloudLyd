package com.lyd;

import io.swagger.annotations.ApiModelProperty;

public class Meta {

    @ApiModelProperty(value="success", hidden=false, notes="表示响应成功或失败",
            required=true, dataType="boolean")// 使用该注解描述属性信息,当hidden=true时，该属性不会在api中显示
    private boolean success;
    @ApiModelProperty(value="message", hidden=false, notes="信息",
            required=true, dataType="string")
    private String message;

    public Meta( boolean success ) {
        this.success = success;
    }

    public Meta( boolean success, String message ) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess( ) {
        return success;
    }

    public String getMessage( ) {
        return message;
    }
}