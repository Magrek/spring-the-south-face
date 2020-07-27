<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="text-capitalize">
        <h5>${username}</h5>
    </div>
    <form method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User Name: </label>
            <div class="col-sm-6">
                <input type="text"
                       name="username"
                       class="form-control"
                       placeholder="User Name"
                       value="${username}"/>
            </div>
            <div style="color: crimson">
                ${usernameError!}
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password: </label>
            <div class="col-sm-6">
                <input type="password"
                       name="password"
                       class="form-control"
                       placeholder="Password"/>
            </div>
            <div style="color: crimson">
                ${passwordError!}
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Email: </label>
            <div class="col-sm-6">
                <input type="email"
                       name="email"
                       class="form-control"
                       placeholder="some@some.com"
                       value="${email!''}"/>
            </div>
            <div style="color: crimson">
                ${emailError!}
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-2">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-dark">SAVE</button>
            </div>

            <#if warningMessage?has_content>
                <div class="col-sm-6 alert alert-warning 1" role="alert">
                    ${warningMessage}
                </div>
            </#if>
        </div>
    </form>
</@c.page>