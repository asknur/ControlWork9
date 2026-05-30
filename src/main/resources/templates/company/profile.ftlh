<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row">
        <div class="col-md-3">
            <div class="card p-3 text-center shadow-sm">
                <#if company.logo??>
                    <img src="/company/logos/${company.logo}" class="img-fluid mb-2" style="max-height:80px">
                </#if>
                <h5>${company.companyName!}</h5>
                <p class="text-muted small">${company.email}</p>
                <form action="/company/logo" method="post" enctype="multipart/form-data">
                    <input type="file" name="logo" class="form-control form-control-sm mb-2" accept="image/*">
                    <button type="submit" class="btn btn-outline-primary btn-sm w-100">Загрузить логотип</button>
                </form>
                <a href="/company/flights/create" class="btn btn-success btn-sm mt-2">+ Создать рейс</a>
            </div>
        </div>
        <div class="col-md-9">
            <h4>Мои рейсы</h4>
            <#if flights?size == 0>
                <div class="alert alert-info">Рейсов пока нет</div>
            <#else>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Номер</th>
                        <th>Маршрут</th>
                        <th>Вылет</th>
                        <th>Прилет</th>
                        <th>Свободных мест</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list flights as f>
                        <tr>
                            <td>${f.flightNumber}</td>
                            <td>${f.departureCity} → ${f.arrivalCity}</td>
                            <td>${f.departureTime}</td>
                            <td>${f.arrivalTime}</td>
                            <td>${f.freeSeats}</td>
                            <td>
                                <a href="/company/flights/${f.id}/tickets"
                                   class="btn btn-sm btn-outline-primary">Билеты</a>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </#if>
        </div>
    </div>
</@main.layout>