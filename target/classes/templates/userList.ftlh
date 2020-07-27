<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="mb-3">
        <h2>List of users</h2>
    </div>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Roles</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>
                    ${user.username}
                </td>
                <td>
                    <#list user.roles?sort?reverse as role>
                        <#assign isAdmin = role == "ADMIN">

                        <#if role == "ADMIN">
                            <#assign style="dark"/>
                        </#if>
                            <span class="badge badge-${isAdmin?string("dark", "success")}">${role}<#sep> </span>
                    </#list>
                </td>
                <td>
                    <a href="/user/${user.id}" class="btn btn-warning btn-sm">Edit</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>