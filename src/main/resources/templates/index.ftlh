<#import "layout.ftlh" as main>
<@main.layout>
    <div class="card p-4 mb-4 shadow-sm">
        <h4 class="mb-3">Поиск рейсов</h4>
        <form method="get" action="/">
            <div class="row g-3">
                <div class="col-md-3">
                    <label class="form-label">Город вылета</label>
                    <input type="text" name="departureCity" class="form-control"
                           value="${departureCity!}" placeholder="Bishkek">
                </div>
                <div class="col-md-3">
                    <label class="form-label">Город прилета</label>
                    <input type="text" name="arrivalCity" class="form-control"
                           value="${arrivalCity!}" placeholder="Moscow">
                </div>
                <div class="col-md-2">
                    <label class="form-label">От</label>
                    <input type="date" name="dateFrom" class="form-control" value="${dateFrom!}">
                </div>
                <div class="col-md-2">
                    <label class="form-label">До</label>
                    <input type="date" name="dateTo" class="form-control" value="${dateTo!}">
                </div>
                <div class="col-md-2 d-flex align-items-end">
                    <button type="submit" class="btn btn-primary w-100">Найти</button>
                </div>
            </div>
        </form>
    </div>

    <#if flights?size == 0>
        <div class="alert alert-info">Рейсов не найдено</div>
    <#else>
        <#list flights as f>
            <div class="card mb-3 shadow-sm">
                <div class="card-body">
                    <div class="row align-items-center">
                        <div class="col-md-2 text-center">
                            <#if f.companyLogo??>
                                <img src="/company/logos/${f.companyLogo}" height="40" alt="${f.companyName!}">
                            </#if>
                            <div class="small text-muted">${f.companyName!}</div>
                            <div class="small text-muted">${f.flightNumber}</div>
                        </div>
                        <div class="col-md-6">
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="text-center">
                                    <div class="fw-bold fs-5">${f.departureTime}</div>
                                    <div>${f.departureCity}</div>
                                </div>
                                <div class="text-muted">✈</div>
                                <div class="text-center">
                                    <div class="fw-bold fs-5">${f.arrivalTime}</div>
                                    <div>${f.arrivalCity}</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2 text-center">
                            <div class="small text-muted">Свободных мест</div>
                            <div class="fw-bold">${f.freeSeats}</div>
                        </div>
                        <div class="col-md-2 text-center">
                            <#if SPRING_SECURITY_CONTEXT?? && SPRING_SECURITY_CONTEXT.authentication??>
                                <#assign auth = SPRING_SECURITY_CONTEXT.authentication>
                                <#assign isUser = false>
                                <#list auth.authorities as a>
                                    <#if a.authority == "ROLE_USER"><#assign isUser = true></#if>
                                </#list>
                                <#if isUser>
                                    <button class="btn btn-primary btn-sm" onclick="showModal(${f.id})">
                                        Забронировать
                                    </button>
                                </#if>
                            <#else>
                                <a href="/auth/login" class="btn btn-outline-primary btn-sm">
                                    Войти для брони
                                </a>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    </#if>

    <#if totalPages gt 1>
        <nav>
            <ul class="pagination justify-content-center mt-3">
                <li class="page-item <#if currentPage == 0>disabled</#if>">
                    <a class="page-link" href="?page=${currentPage - 1}&departureCity=${departureCity!}&arrivalCity=${arrivalCity!}&dateFrom=${dateFrom!}&dateTo=${dateTo!}">Назад</a>
                </li>
                <#list 0..(totalPages - 1) as p>
                    <li class="page-item <#if p == currentPage>active</#if>">
                        <a class="page-link" href="?page=${p}&departureCity=${departureCity!}&arrivalCity=${arrivalCity!}&dateFrom=${dateFrom!}&dateTo=${dateTo!}">${p + 1}</a>
                    </li>
                </#list>
                <li class="page-item <#if currentPage == totalPages - 1>disabled</#if>">
                    <a class="page-link" href="?page=${currentPage + 1}&departureCity=${departureCity!}&arrivalCity=${arrivalCity!}&dateFrom=${dateFrom!}&dateTo=${dateTo!}">Вперёд</a>
                </li>
            </ul>
        </nav>
    </#if>

    <div class="modal fade" id="bookingModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Выбор места</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body" id="modal-body">Загрузка...</div>
            </div>
        </div>
    </div>

    <script>
        function showModal(flightId) {
            fetch('/booking/tickets/' + flightId)
                .then(function(r) { return r.text(); })
                .then(function(html) {
                    document.getElementById('modal-body').innerHTML = html;
                    new bootstrap.Modal(document.getElementById('bookingModal')).show();
                });
        }
    </script>
</@main.layout>