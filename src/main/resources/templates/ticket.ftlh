<h6>Бизнес класс</h6>
<div class="mb-3">
    <#list tickets as t>
        <#if t.seatClass == "BUSINESS">
            <form action="/booking/book/${t.id}" method="post" class="d-inline m-1">
                <button class="btn btn-sm <#if t.booked>btn-danger disabled<#else>btn-outline-success</#if>">
                    ${t.seatNumber}<br>$${t.price?string["#,##0"]}
                </button>
            </form>
        </#if>
    </#list>
</div>

<h6>Эконом класс</h6>
<div>
    <#list tickets as t>
        <#if t.seatClass == "ECONOMY">
            <form action="/booking/book/${t.id}" method="post" class="d-inline m-1">
                <button class="btn btn-sm <#if t.booked>btn-danger disabled<#else>btn-outline-success</#if>">
                    ${t.seatNumber}<br>$${t.price?string["#,##0"]}
                </button>
            </form>
        </#if>
    </#list>
</div>