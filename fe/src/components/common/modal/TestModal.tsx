import { useModal } from './useModal';

export const TestModal: React.FC<TestModalProps> = ({ name, age }) => {
  const { closeModal } = useModal<'test'>();

  return (
    <div>
      Name: {name}, Age: {age}
      <button onClick={() => closeModal('test')}>Close</button>
    </div>
  );
};

export const Test2Modal: React.FC<Test2ModalProps> = ({ title, content }) => {
  const { closeModal } = useModal<'test2'>();

  return (
    <div>
      Title: {title}, Content: {content}
      <button onClick={() => closeModal('test2')}>Close</button>
    </div>
  );
};

export const TemporaryComponent = () => {
  const { openModal, closeModal } = useModal<'test'>();

  const handleOpenTestModal = () => {
    openModal('test', {
      name: 'John',
      age: 30,
    });
  };

  return (
    <div>
      <button onClick={handleOpenTestModal}>Open Test Modal</button>
      <button onClick={() => closeModal('test')}>Close Test Modal</button>
    </div>
  );
};
