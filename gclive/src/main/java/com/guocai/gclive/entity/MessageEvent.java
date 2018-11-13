package com.guocai.gclive.entity;

/**
 * Create  By xrj ON 2018/9/20 0020
 */
public class MessageEvent {
    private String message;
    private String name;
    private String leave;
    public  MessageEvent(String message,String name,String leave){
        this.message=message;
        this.name=name;
        this.leave=leave;
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
