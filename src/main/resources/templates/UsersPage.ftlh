<#import "commons.ftlh" as c>
<#import "/spring.ftl" as spring>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Created</th>
                <#if user??>
                    <th>Enable\Disable</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <#if users?size==0>
                <tr class="noData">
                    <td colspan="3">
                        No data
                    </td>
                </tr>
            <#else>
                <#list users as userIter>
                    <tr>
                        <td class="id">${userIter.id}</td>
                        <td class="login"><@c.userLink userIter/></td>
                        <td class="creationTime">${userIter.creationTime}</td>

                        <#if user??>
                            <td class="user-is-disabled">
                                <form method="post" action="<@spring.url '/users/all/disable'/>">
                                    <#assign status = userIter.disabled?string('enable', 'disable')/>
                                    <input type="hidden" name="status" value="${status}"/>
                                    <input type="hidden" name="id" value="${userIter.id}"/>
                                    <button type="submit">${status}</button>
                                </form>
                            </td>
                        </#if>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>
</@c.page>
