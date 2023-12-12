type ModalType = keyof ModalPropsMap;

type ModalPropsMap = {
  test: TestModalProps;
  test2: Test2ModalProps;
  commentAlert: CommentAlertProps;
};

type Modal<T extends ModalType> = {
  id: T;
  isOpen: boolean;
  props: ModalPropsMap[T] | null;
};

type TestModalProps = {
  name: string;
  age: number;
};

type Test2ModalProps = {
  title: string;
  content: string;
};

type CommentAlertProps = {
  onEdit?(): void;
  onDelete?(): void;
  onClose?(): void;
  onReport?(): void;
};
