<#import "../layout.ftlh" as main>
<@main.layout>
    <h4>Рейс ${flight.flightNumber}: ${flight.departureCity} → ${flight.arrivalCity}</h4>
    <p class="text-muted">${flight.departureTime?string("dd.MM.yyyy HH:mm")}</p>
    <table class="table table-striped">
        <thead>
        <tr><th>Место</th><th>Класс</th><th>Цена</th><th>Статус</th></tr>
        </thead>
        <tbody>
        <#list tickets as t>
            <tr>
                <td>${t.seatNumber}</td>
                <td>${t.seatClass}</td>
                <td>$${t.price?string["#,##0.00"]}</td>
                <td>
                    <#if t.booked?? && t.booked>
                        <span class="badge bg-danger">Занято</span>
                    <#else>
                        <span class="badge bg-success">Свободно</span>
                    </#if>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    <a href="/company/profile" class="btn btn-outline-secondary">Назад</a>
</@main.layout>