<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-3">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control" placeholder="Filter By title" value="${filter!}">
                <button type="submit" class="btn btn-info ml-2">Filter</button>
            </form>
        </div>
    </div>

    <a class="btn btn-dark"
       data-toggle="collapse"
       href="#addNewProductCollapse"
       role="button"
       aria-expanded="false"
       aria-controls="collapseExample">
        Add New Product
    </a>
    <div class="collapse" id="addNewProductCollapse">
        <div class="form-group mt-3 col-6">
            <form method="post" action="create" enctype="multipart/form-data">
                <div style="color: crimson">
                    ${titleFieldError!}
                </div>
                <div class="form-group">
                    <input class="form-control"
                           type="text"
                           name="title"
                           placeholder="Enter product title"
                           value="${enteredTitle!""}"/>
                </div>

                <div style="color: crimson">
                    ${priceFieldError!}
                </div>
                <div class="input-group my-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">$</span>
                    </div>
                    <input class="form-control"
                           type="text"
                           name="price"
                           placeholder="Enter product price"
                           value="${enteredPrice!""}"/>
                </div>

                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button class="btn btn-dark" type="submit">Create</button>
                </div>
            </form>
        </div>
    </div>

    <div class="card-columns">
        <#list products as product>
            <div class="card my-3" style="width: 18rem;">
                <div class="col-xs-3 product-image-wrapper">
                    <#assign productPlaceholderImage = "product-placeholder-image.jpg"/>
                    <img class="card-img-top product-image"
                         src="/img/${product.filename!productPlaceholderImage}"
                         onerror="this.src='/img/${productPlaceholderImage}';"/>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">${product.title}</li>
                    <li class="list-group-item">${product.price}$</li>
                </ul>
                <div class="card-body row">
                    <a href="#" class="btn btn-outline-dark mx-2">ADD TO BAG</a>
                    <a href="#" class="btn btn-outline-dark mx-1">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-heart" fill="currentColor"
                             xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                  d="M8 2.748l-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
                        </svg>
                    </a>
                </div>
            </div>
        <#else>
            ${product_message}
        </#list>
    </div>
</@c.page>