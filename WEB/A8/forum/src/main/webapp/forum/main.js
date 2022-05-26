const getItemElement = (currentItem, userId) => `
    <div class="post-item card">
        ${currentItem.authorId === userId ? `
        <div class="actions-container">
            <button onclick="handleDelete(${currentItem.id})">delete</button>
        </div>` : ''}
        <h2 class="post-title">${currentItem.title}</h2>
        <p class="post-content">${currentItem.content}</p>
        <span class="post-author">&ndash; ${currentItem.authorName}</span>
        <br>
        <div style="margin: 16px 0; padding: 4px; border: 2px solid #ccc; display: ${currentItem.comments.length > 0 ? 'block' : 'none'}">
            ${getComments(currentItem.comments, userId)}
        </div>
        <input id="add-comment-input-${currentItem.id}" type="text" placeholder="enter comment...">
        <button onclick="addComment(${currentItem.id})">Add comment</button>
    </div>`

const getComments = (comments, userId) =>
    comments.reduce((acc, currentItem) => acc + `
        <div class="comment-item">
            <span>${currentItem.text}</span>
            <span>&ndash;${currentItem.authorName}</span>
            ${currentItem.authorId === userId ? `<button style="float: right" onclick="deleteComment(${currentItem.id})">delete</button>` : ''}
        </div>`, '');

