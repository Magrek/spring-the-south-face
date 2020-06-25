<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div>
        <@l.logout />
    </div>

    <div>
        <form action="/bag">
            <input type="submit" value="Bag">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>

    <div>
        <form method="post" action="create">
            <input type="text" name="title" placeholder="Enter product title"/>
            <input type="text" name="price" placeholder="Enter product price"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Create</button>
        </form>
    </div>

    <div>All Products</div>
    <form method="get" action="main">
        <input type="text" name="filter" placeholder="Filter By title" value="${filter!""}">
        <button type="submit">Filter</button>
    </form>
    <#list products as product>
        <div>
            <span>${product.title}</span>
            <i>${product.price}</i>
        </div>
    <#else>
        ${product_message}
    </#list>
</@c.page>