<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">


<@c.page>
    <h5>Hello, ${username}</h5>
    <div>Lets suit up!</div>
    <div class="mt-3">
        <#if warningMessage??>
            <div class="alert alert-warning" role="alert">
                ${warningMessage}
            </div>
        </#if>
        <#if successMessage??>
            <div class="alert alert-success" role="alert">
                ${successMessage}
            </div>
        </#if>
        <#if dangerMessage??>
            <div class="alert alert-danger" role="alert">
                ${dangerMessage}
            </div>
        </#if>
    </div>
</@c.page>