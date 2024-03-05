<#import "commons.ftlh" as c>

<@c.page>
    <div class="notice-publish form-box">
        <div class="header">New notice</div>
        <div class="body">
            <form method="post" action="">
                <div class="field">
                    <div class="name">
                        <label for="content">Notice text</label>
                    </div>
                    <div class="value">
                        <textarea id="content" name="content">${noticeForm.content!}</textarea>
                    </div>
                    <@c.error "noticeForm.content"/>
                </div>
                <div class="button-field">
                    <input type="submit" value="Publish">
                </div>
            </form>
        </div>
    </div>
</@c.page>
