<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<#assign
isLoggedIn = false
username = "Guest"
isAdmin = false
/>

<@security.authorize access="isAuthenticated()">
    <#assign isLoggedIn = true/>
</@security.authorize>

<#if isLoggedIn>
    <#assign user>
        <@security.authentication property="principal"/>
    </#assign>

    <#assign username>
        <@security.authentication property="principal.username"/>
    </#assign>

    <@security.authorize access="hasRole('ADMIN')">
        <#assign isAdmin = true/>
    </@security.authorize>
</#if>