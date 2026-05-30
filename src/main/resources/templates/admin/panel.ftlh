<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="d-flex justify-content-between mb-3">
        <h2>Панель администратора</h2>
        <a href="/admin/companies/create" class="btn btn-success">+ Создать компанию</a>
    </div>
    <#if error??><div class="alert alert-danger">${error}</div></#if>
    <table class="table table-striped">
        <thead>
        <tr><th>Название</th><th>Email</th><th>Статус</th><th></th></tr>
        </thead>
        <tbody>
        <#list companies as c>
            <tr>
                <td>${c.companyName!}</td>
                <td>${c.email}</td>
                <td>
                    <#if c.enabled?? && c.enabled>
                        <span class="badge bg-success">Активна</span>
                    <#else>
                        <span class="badge bg-danger">Заморожена</span>
                    </#if>
                </td>
                <td>
                    <form action="/admin/companies/toggle/${c.id}" method="post" class="d-inline">
                        <button type="submit" class="btn btn-sm
                        <#if c.enabled?? && c.enabled>btn-warning<#else>btn-success</#if>">
                            <#if c.enabled?? && c.enabled>Заморозить<#else>Разморозить</#if>
                        </button>
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@main.layout>