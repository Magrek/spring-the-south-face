<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-3">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control" placeholder="Filter By title" value="${filter!}">
                <button type="submit" class="btn btn-primary ml-2">Filter</button>
            </form>
        </div>
    </div>

    <a class="btn btn-primary"
       data-toggle="collapse"
       href="#addNewProductCollapse"
       role="button"
       aria-expanded="false"
       aria-controls="collapseExample">
        Add New Product
    </a>
    <div class="collapse" id="addNewProductCollapse">
        <div class="form-group">
            <form method="post" action="create" enctype="multipart/form-data">
                <div style="color: crimson">
                    ${titleFieldError!}
                </div>
                <div>
                    <input class="form-control"
                           type="text"
                           name="title"
                           placeholder="Enter product title"
                           value="${enteredTitle!""}"/>
                </div>

                <div style="color: crimson">
                    ${priceFieldError!}
                </div>
                <div>
                    <input class="form-control"
                           type="text"
                           name="price"
                           placeholder="Enter product price"
                           value="${enteredPrice!""}"/>
                </div>

                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div>
                    <button class="btn btn-primary" type="submit">Create</button>
                </div>
            </form>
        </div>
    </div>

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