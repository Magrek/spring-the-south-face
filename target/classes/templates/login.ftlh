<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    Login Page
    <div style="color: crimson">${message?has_content}</div>
    <@l.login "/login", "Sign In", false/>
</@c.page>