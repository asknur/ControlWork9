<#import "../layout.ftlh" as main>
<@main.layout>
    <h2 class="mb-4">Мои бронирования</h2>
    <#if bookings?size == 0>
        <div class="alert alert-info">Бронирований пока нет</div>
    <#else>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Рейс</th>
                <th>Маршрут</th>
                <th>Вылет</th>
                <th>Место</th>
                <th>Класс</th>
                <th>Цена</th>
            </tr>
            </thead>
            <tbody>
            <#list bookings as b>
                <tr>
                    <td>${b.flightNumber}</td>
                    <td>${b.departureCity} → ${b.arrivalCity}</td>
                    <td>${b.departureTime}</td>
                    <td>${b.seatNumber}</td>
                    <td>${b.seatClass}</td>
                    <td>$${b.price?string["#,##0.00"]}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </#if>
</@main.layout>