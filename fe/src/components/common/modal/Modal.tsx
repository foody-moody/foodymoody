import loadable from '@loadable/component';
import { createPortal } from 'react-dom';
import { useRecoilValue } from 'recoil';
import { modalListState } from 'recoil/modal/atom';
import { modalSelector } from 'recoil/modal/selector';
import { AccountAlert } from './AccountAlert';
import { CollectionAlert } from './CollectionAlert';
// import { CollectionModal } from './CollectionModal';
import { CommentAlert } from './CommentAlert';
import { ProfileImageAlert } from './ProfileImageAlert';
import { TestModal, Test2Modal } from './TestModal';

const CollectionModal = loadable(() =>
  import('./CollectionModal').then((module) => module.CollectionModal)
);

const MODAL_COMPONENTS: {
  [key in ModalType]: React.ComponentType<ModalPropsMap[key]>;
} = {
  commentAlert: CommentAlert,
  accountAlert: AccountAlert,
  collection: CollectionModal,
  collectionAlert: CollectionAlert,
  profileImageAlert: ProfileImageAlert,
  test: TestModal,
  test2: Test2Modal,
};

export const ModalItem: React.FC<{ id: ModalType }> = ({ id }) => {
  const modalData = useRecoilValue(modalSelector(id));
  if (!modalData.isOpen || !modalData.props) return null;
  const ModalComponent = MODAL_COMPONENTS[id] as React.ComponentType<
    ModalPropsMap[typeof id]
  >;
  return <ModalComponent {...modalData.props} />;
};

export const GlobalModals = () => {
  const modalList = useRecoilValue(modalListState);

  return createPortal(
    <>
      {modalList.map((id) => (
        <ModalItem key={id} id={id} />
      ))}
    </>,
    document.getElementById('modal') as HTMLElement
  );
};
