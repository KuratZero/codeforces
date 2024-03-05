<#import "commons.ftlh" as c>

<@c.page>
    <#if foundUser??>
        <@userBlock foundUser/>
    <#else>
        Not found user by parameters
    </#if>
</@c.page>

<#macro userBlock user>
    <div class="user-block">
        <div class="left">
            <@c.userLink user/>
            <ul>
                <li>id: ${user.id}</li>
                <li>login: ${user.login}</li>
                <li>creationTime: ${user.creationTime}</li>
            </ul>
        </div>
        <div class="right">
            <img src="https://userpic.codeforces.org/no-title.jpg" alt="avatar"/>
        </div>
    </div>
</#macro>