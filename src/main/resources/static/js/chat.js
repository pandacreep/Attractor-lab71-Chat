'use strict';

const serverPath = 'http://localhost:8080/posts';
const chatId = document.getElementById('info').getAttribute('data-chatId');
const key = 'postCount-' + chatId;
localStorage.setItem(key, 0);
getPosts();

async function getRestData(url) {
    const data = await fetch(url, {cache: 'no-cache'});
    return data.json();
};

async function getPosts() {
    const url = `${serverPath}/` + chatId;
    const data = await getRestData(url);
    let posts = data.posts;
    document.getElementById('posts').innerHTML = "";
    let postsNumber = posts.length - 20;
    if (postsNumber < 0 ) {
        postsNumber = 0;
    }
    for (let i = postsNumber; i < posts.length; i++) {
        showPost(posts[i]);
    }
    localStorage.setItem(key, posts.length);
    setTimeout(getPosts, 1000);
}

function showPost(post) {
    const postRow = document.createElement('div');
    postRow.innerHTML = createPostHtml(post);
    document.getElementById("posts").append(postRow);
}

function createPostHtml(post) {
    return `
        <p>
            <span>${post.user.email}>> </span>
            <span>${post.message}</span>
        </p>
    `;
}
