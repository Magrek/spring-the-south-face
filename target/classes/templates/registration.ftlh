<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div class="mb-1">Add new user</div>
    <div style="color: crimson">${message?has_content}</div>
    <@l.login "/registration", "Create User", true/>
</@c.page>