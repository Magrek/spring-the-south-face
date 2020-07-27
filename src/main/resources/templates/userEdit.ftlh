<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="mb-3">
        <h2>User editor</h2>
    </div>

    <form action="/user" method="post">
        <div class="form-group">
            <label for="usernameInput">Username</label>
            <input class="form-control col-6" type="text" name="username" id="usernameInput" value="${user.username}"/>
            <#list roles as role>
                <div class="form-group form-check mt-3">
                    <input type="checkbox" class="form-check-input" id="${role}Checkbox"
                           name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}/>
                    <label class="form-check-label" for="${role}Checkbox">${role}</label>
                </div>
            </#list>
            <input type="hidden" value="${user.id}" name="userID"/>
            <input type="hidden" value="${_csrf.token}" name="_csrf"/>
            <button type="submit" class="btn btn-dark">Save</button>
        </div>
    </form>
</@c.page>