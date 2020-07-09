<#macro login path, button, isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User Name: </label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" placeholder="User Name"/>
            </div>
            <div style="color: crimson">
                ${usernameError!}
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password: </label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Password"/>
            </div>
            <div style="color: crimson">
                ${passwordError!}
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-dark">${button}</button>
        <#if !isRegisterForm>
            <a href="/registration" class="btn btn-dark">Create User</a>
        </#if>
    </form>
</#macro>
<#macro logout>
    <div>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-dark">Sign Out</button>
        </form>
    </div>
</#macro>