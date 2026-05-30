<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow p-4">
                <h3 class="mb-4">Создать рейс</h3>
                <form action="/company/flights/create" method="post">
                    <div class="mb-3">
                        <label class="form-label">Город вылета</label>
                        <input type="text" name="departureCity" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Город прилета</label>
                        <input type="text" name="arrivalCity" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Дата и время вылета</label>
                        <input type="datetime-local" name="departureTime" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Дата и время прилета</label>
                        <input type="datetime-local" name="arrivalTime" class="form-control" required>
                    </div>
                    <div class="d-flex gap-2">
                        <a href="/company/profile" class="btn btn-outline-secondary">Отмена</a>
                        <button type="submit" class="btn btn-primary">Создать</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@main.layout>