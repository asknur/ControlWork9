<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row justify-content-center">
        <div class="col-md-4">
            <div class="card shadow p-4">
                <h3 class="text-center mb-4">Регистрация</h3>
                <#if error??><div class="alert alert-danger">${error}</div></#if>
                <form action="/auth/register" method="post">
                    <div class="mb-3">
                        <label class="form-label">Полное имя</label>
                        <input type="text" name="fullName" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <input type="email" name="email" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Пароль</label>
                        <input type="password" name="password" class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-success w-100">Зарегистрироваться</button>
                </form>
                <div class="text-center mt-3">
                    <a href="/auth/login">Уже есть аккаунт? Войти</a>
                </div>
            </div>
        </div>
    </div>
</@main.layout>