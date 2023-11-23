<template>
    <div class="confirmation-modal">
      <div class="modal-content">
        <p>{{ message }}</p>
        <div class="buttons">
          <button @click="confirm">확인</button>
          <button @click="cancel">취소</button>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      message: String,
      onConfirm: Function,
      onCancel: Function
    },
    methods: {
      confirm() {
        this.onConfirm();
      },
      cancel() {
        this.onCancel();
      }
    }
  };
  </script>
  
  <style scoped>
  /* 스타일링을 원하는 대로 추가하세요. */
  .confirmation-modal {
    /* 모달 스타일링 */
  }
  
  .modal-content {
    /* 모달 내용 스타일링 */
  }
  
  .buttons {
    /* 버튼 스타일링 */
  }
  </style>
  