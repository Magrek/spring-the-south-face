<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div>
        <@l.logout/>
    </div>

    <div>
        <span><a href="/user">User list</a></span>
    </div>

    <div>
        <form method="post" action="create" enctype="multipart/form-data">
            <div style="color: crimson">${titleFieldError?ifExists}</div>
            <input type="text" name="title" placeholder="Enter product title" value="${enteredTitle!""}"/>
            <div style="color: crimson">${priceFieldError?ifExists}</div>
            <input type="text" name="price" placeholder="Enter product price" value="${enteredPrice!""}"/>
            <input type="file" name="file">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div>
                <button type="submit">Create</button>
            </div>
        </form>
    </div>

    <div>All Products</div>
    <form method="get" action="main">
        <input type="text" name="filter" placeholder="Filter By title" value="${filter?ifExists}">
        <button type="submit">Filter</button>
    </form>
    <#list products as product>
        <div>
            <span>${product.title}</span>
            <i>${product.price}</i>
            <div>
                <#if product.filename??>
                    <img src="/img/${product.filename}">
                </#if>
            </div>
        </div>
    <#else>
        ${product_message}
    </#list>
</@c.page>