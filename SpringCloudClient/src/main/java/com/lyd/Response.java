package com.lyd;


import io.swagger.annotations.ApiModelProperty;

public class Response {

    private static final String OK = "ok";
    private static final String ERROR = "error";

    private Meta meta;
    @ApiModelProperty(value="data", hidden=false, notes="数据",
            required=true, dataType="data")// 使用该注解描述属性信息,当hidden=true时，该属性不会在api中显示
    private Object data;

    public Response success( ) {
        this.meta = new Meta( true, OK );
        return this;
    }

    public Response success( Object data ) {
        this.meta = new Meta( true, OK );
        this.data = data;
        return this;
    }

    public Response failure( ) {
        this.meta = new Meta( false, ERROR );
        return this;
    }

    public Response failure( String message ) {
        this.meta = new Meta( false, message );
        return this;
    }

    public Meta getMeta( ) {
        return meta;
    }

    public Object getData( ) {
        return data;
    }


}
