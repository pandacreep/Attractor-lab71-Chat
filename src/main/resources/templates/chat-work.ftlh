<#import "partials/main.ftlh" as main>

<@main.renderWith scripts=["/js/chat.js"]>

    <h1 id="info" data-chatId="${chatId}" class="my-3 text-center">Chat '${chatId}'</h1>
    <p>You logged as ${email}</p>
    <form action="add-post" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <div class="row">
            <div class="col-3">
                <label class="flex flex-v-center">
                    <span class="label-input-text mb-1">Message: </span>
                    <input type="text" name="message">
                    <input type="text" name="email" value="${email}" hidden>
                    <input type="text" name="chatId" value="work" hidden>
                </label>
            </div>
            <div class="col-3">
                <button class="btn btn-primary" type="submit">Send</button>
            </div>
        </div>
    </form>
    <div id="posts">
    </div>

</@main.renderWith>