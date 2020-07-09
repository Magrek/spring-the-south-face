<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">


<@c.page>
    <div style="color: green">${message!}</div>
    <h5>Hello, ${username}</h5>
    <div>Lets suit up!</div>
</@c.page>