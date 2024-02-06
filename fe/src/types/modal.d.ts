type ModalType = keyof ModalPropsMap;

type ModalPropsMap = {
  test: TestModalProps;
  test2: Test2ModalProps;
  commentAlert: CommentAlertProps;
  accountAlert: AccountAlertProps;
  collection: CollectionModalProps;
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

type CollectionModalProps = {
  type: 'default' | 'addFeed';
};

type CommentAlertProps = {
  onEdit?(): void;
  onDelete?(): void;
  onClose?(): void;
  onReport?(): void;
};

type AccountAlertProps = {
  onDelete?(): void;
  onClose?(): void;
};
