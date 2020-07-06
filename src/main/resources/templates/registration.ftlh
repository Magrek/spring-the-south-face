<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    Add new user
    <div style="color: crimson">${message!}</div>
    <@l.login "/registration", "Create User", true/>
</@c.page>