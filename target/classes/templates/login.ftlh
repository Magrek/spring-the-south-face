<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
Login Page
<@l.login "/login", "Sign In"/>
<a href="/registration">CREATE USER</a>
</@c.page>