<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div class="mb-1">Add new user</div>
    <#if message?hasContent>
        <div style="color: crimson">${message}</div>
    </#if>
    <@l.login "/registration", "Create User", true/>
</@c.page>