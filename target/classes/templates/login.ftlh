<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    Login Page
    <#if message?hasContent>
        <div style="color: crimson">${message}</div>
    </#if>
    <@l.login "/login", "Sign In", false/>
</@c.page>